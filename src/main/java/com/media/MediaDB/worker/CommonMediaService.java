package com.media.MediaDB.worker;

import java.util.List;

import com.media.MediaDB.co.MediaSearchCO;
import com.media.MediaDB.dto.MediaSearchDTO;
import com.media.MediaDB.entities.MediaFile;

public interface CommonMediaService {

	public MediaSearchDTO searchMediaFiles(MediaSearchCO searchCO);

}
