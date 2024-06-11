package himedia.dao;

import java.util.List;
import himedia.vo.BookListVo;

public interface GuestbookDao {
    List<BookListVo> getList();
    boolean add(BookListVo vo);
    boolean delete(BookListVo vo);
    BookListVo get(Long no);
}