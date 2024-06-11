package himedia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import himedia.vo.BookListVo;

public class GuestBookDaoOracleImpl implements GuestbookDao {
    private String dbuser;
    private String dbpass;

    public GuestBookDaoOracleImpl(String dbuser, String dbpass) {
        this.dbuser = dbuser;
        this.dbpass = dbpass;
    }

    private Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
            conn = DriverManager.getConnection(dburl, dbuser, dbpass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public List<BookListVo> getList() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<BookListVo> list = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT no, name, password, content, reg_date FROM guestbook ORDER BY reg_date DESC";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Long no = rs.getLong("no");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String content = rs.getString("content");
                Date regDate = rs.getDate("reg_date");
                BookListVo vo = new BookListVo(no, name, password, content, regDate);
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    @Override
	public BookListVo get(Long id) {
		BookListVo tmp = null;
		String sql = """
				SELECT no, name, password, content, reg_date
				FROM guestbook
				WHERE no = ?
				""";
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = executeQuery(pstmt, id);
		) {
			if (rs.next()) {
				Long no = rs.getLong("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				Date regDate = rs.getDate("reg_date");
				
				tmp = new BookListVo(no, name, password, content, regDate);
				}
		} catch (SQLException e) {
			System.err.println("연결 에러");
		} catch (Exception e) {
			System.err.println("에러");
		}
		return tmp;
	}

    private ResultSet executeQuery(PreparedStatement pstmt, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(BookListVo vo) {
		int insertCount = 0;
		String sql = """
				INSERT INTO guestbook(no, name, password, content, reg_date)
				VALUES(seq_guestbook_no.nextval, ?, nvl(?, ''), ?, sysdate)
				""";
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
		) {
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("연결 에러");
		} catch (Exception e) {
			System.err.println("에러");
		}
		return insertCount == 1;
	}
    @Override
    public boolean delete(BookListVo vo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int deletedCount = 0;

        try {
            conn = getConnection();
            String sql = "DELETE FROM guestbook WHERE no=? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, vo.getNo());
            deletedCount = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletedCount == 1;
    }
}