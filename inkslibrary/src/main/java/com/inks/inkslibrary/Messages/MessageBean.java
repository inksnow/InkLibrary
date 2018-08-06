package com.inks.inkslibrary.Messages;

/**
 * Created by inks on 2018/8/3 0003.
 */

public class MessageBean {
    //命令标志
    private String flag = "";
    //命令数据
    private byte[] datas;
    //最多发送次数
    private int repeatTimes = 1;
    //是否移除相同标志的其他消息（如果相同标志的消息正在处理，则移除自己）
    private boolean deleteSame = true;
    //是否正在处理
    private boolean processing = false;
    //是否插队（如果没有正在处理的消息，则插在第一位，如果有，则插入第二位）
    private boolean queueUp = false;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public byte[] getDatas() {
        return datas;
    }

    public void setDatas(byte[] datas) {
        this.datas = datas;
    }

    public int getRepeatTimes() {
        return repeatTimes;
    }

    public void setRepeatTimes(int repeatTimes) {
        this.repeatTimes = repeatTimes;
    }

    public boolean isDeleteSame() {
        return deleteSame;
    }

    public void setDeleteSame(boolean deleteSame) {
        this.deleteSame = deleteSame;
    }

    public boolean isProcessing() {
        return processing;
    }

    public void setProcessing(boolean processing) {
        this.processing = processing;
    }

    public boolean isQueueUp() {
        return queueUp;
    }

    public void setQueueUp(boolean queueUp) {
        this.queueUp = queueUp;
    }
}
