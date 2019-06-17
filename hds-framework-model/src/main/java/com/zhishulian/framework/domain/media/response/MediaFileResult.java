package com.zhishulian.framework.domain.media.response;

import com.zhishulian.framework.domain.media.MediaFile;
import com.zhishulian.framework.model.response.ResponseResult;
import com.zhishulian.framework.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mrt on 2018/3/31.
 */
@Data
@NoArgsConstructor
public class MediaFileResult extends ResponseResult {
    MediaFile mediaFile;
    public MediaFileResult(ResultCode resultCode, MediaFile mediaFile) {
        super(resultCode);
        this.mediaFile = mediaFile;
    }
}
