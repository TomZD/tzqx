package cn.movinginfo.tztf.releasechannel;

import cn.movinginfo.tztf.sev.domain.MiddleObject;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;

public interface Channel {
    
    public void doRelease       (String content,  MiddleObject midObj) throws ChannelReleaseException;
    
    public void doReleaseFile   (String filePath, MiddleObject midObj) throws ChannelReleaseException;
    
    /**
     * 接口手动解除时的行为
     * @param content
     * @param midObj
     * @throws ChannelReleaseException
     */
    public void doRemove        (String content,  MiddleObject midObj) throws ChannelReleaseException;
    
    /**
     * 接口自动解除时的行为
     * @param content
     * @param midObj
     * @throws ChannelReleaseException
     */
    public void doDefaultRemove (String content,  MiddleObject midObj) throws ChannelReleaseException;
    
    public void setChannelInstance(ReleaseChannelInstance channelInstance);
}
