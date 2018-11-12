package test;

import com.baizhi.dao.BannerDAO;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import util.BaticTest;

import java.util.Date;
import java.util.List;

public class BannerTest  extends BaticTest {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private BannerDAO bannerDAO;
    @Test
    public void queryAll(){
        List<Banner> bannerAll = bannerService.findBannerAll();
        for (Banner banner : bannerAll) {
            System.out.println(banner);
        }
    }

    @Test
    public void count(){
        Long aLong = bannerDAO.queryBannerTotals();
        System.out.println(aLong);
    }

    @Test
    public void page(){
        List<Banner> bannerByPage = bannerService.findBannerByPage(1, 3);
        for (Banner banner : bannerByPage) {
            System.out.println(banner);
        }
    }

    @Test
    public void add(){
        Banner banner=new Banner();
        banner.setDescs("xx");
        banner.setCreateTime(new Date());
        banner.setImgPaths("1.jpg");
        banner.setTitle("xxxxxxx");
        banner.setStatus("y");
        bannerService.addBanner(banner);
    }
    @Test
    public void update(){
        Banner banner=new Banner();
        banner.setId("ff89acb2-5ec5-4b5e-939a-d23ee5834ecf");
        banner.setTitle("12356");
        bannerService.motifyBanner(banner);
    }
}
