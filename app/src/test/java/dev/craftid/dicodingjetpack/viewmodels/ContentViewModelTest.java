package dev.craftid.dicodingjetpack.viewmodels;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import dev.craftid.dicodingjetpack.data.ContentEntity;

import static org.junit.Assert.*;

public class ContentViewModelTest {

    private ContentViewModel contentViewModel;
    private ContentEntity dummyData;

    @Before
    public void setUp() throws Exception {
        contentViewModel = new ContentViewModel();
        dummyData = new ContentEntity(
                ContentEntity.TYPE_TV_SHOW,
                20,
                "5.8",
                "Arrow",
                "Arrow",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg",
                "Arrow",
                "https://img.youtube.com/vi/nSbzyEJ8X9E/hqdefault.jpg",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                null,
                "October 10, 2012"
        );
    }

    @Test
    public void getMovies() {
        List<ContentEntity> movieList = contentViewModel.getMovies();
        assertNotNull(movieList);
        assertEquals(20, movieList.size());
    }

    @Test
    public void getTvShows() {
        List<ContentEntity> tvShowList = contentViewModel.getTvShows();
        assertNotNull(tvShowList);
        assertEquals(10, tvShowList.size());
    }

    @Test
    public void getDetailContent() {
        ContentEntity contentEntity = contentViewModel.getDetailContent(dummyData.getIdContent());
        assertNotNull(contentEntity);
        assertEquals(dummyData.getIdContent(), contentEntity.getIdContent());
    }

    @Test
    public void getSearchResult() {
        List<ContentEntity> contentEntityList = contentViewModel.getSearchResult(dummyData.getTitle());
        assertNotNull(contentEntityList);
        assertEquals(dummyData.getTitle(), contentEntityList.get(0).getTitle());
    }
}