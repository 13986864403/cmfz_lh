package test;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AlbumTest extends  BannerTest {
    @Autowired
    private AlbumService albumService;
    @Test
    public void queryAll(){
        List<Album> all = albumService.findAll();
        for (Album album : all) {
            System.out.println(album);
        }
    }

    @Test
    public void queryPage(){
        List<Album> byPage = albumService.findByPage(1, 2);
        for (Album album : byPage) {
            System.out.println(album);
        }
    }
@Test
    public void insert(){
        Album album=new Album();
        album.setImages("2.jpg");
        album.setAuthor("张三");
        album.setBlues(12);
        album.setBroadcasting("zhangsans kja");
        album.setContent("shdsb");
        albumService.addAlbum(album);
}

}
