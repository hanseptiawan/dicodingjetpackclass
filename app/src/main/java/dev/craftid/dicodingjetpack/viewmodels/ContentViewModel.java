package dev.craftid.dicodingjetpack.viewmodels;

import androidx.lifecycle.ViewModel;

import java.util.List;

import dev.craftid.dicodingjetpack.data.ContentEntity;
import dev.craftid.dicodingjetpack.utils.DataDummy;

public class ContentViewModel extends ViewModel {

     public List<ContentEntity> getMovies(){
         return DataDummy.getMovieDummies();
     }

     public List<ContentEntity> getTvShows(){
         return DataDummy.getTVShowDummies();
     }

     public ContentEntity getDetailContent(int contentID){
         return DataDummy.getDetailContent(contentID);
     }

     public List<ContentEntity> getSearchResult(String keywords){
         return DataDummy.searchContent(keywords);
     }

}
