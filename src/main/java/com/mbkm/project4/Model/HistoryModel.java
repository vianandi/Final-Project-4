package com.mbkm.project4.Model;

public class HistoryModel {

    private String mIdBook;
    private String mTanggal;
    private String mRiwayat;
    private String mTotal;
    private String mBus;
    private int mImageResourceId;
    private static final int NO_IMAGE_PROVIDED = -1;

    public HistoryModel(String mIdBook, String mTanggal, String mRiwayat, String mTotal, String mBus, int mImageResourceId) {
        this.mIdBook = mIdBook;
        this.mTanggal = mTanggal;
        this.mRiwayat = mRiwayat;
        this.mTotal = mTotal;
        this.mBus = mBus;
        this.mImageResourceId = mImageResourceId;
    }

    public String getmIdBook() {
        return mIdBook;
    }

    public void setmIdBook(String mIdBook) {
        this.mIdBook = mIdBook;
    }

    public String getmTanggal() {
        return mTanggal;
    }

    public void setmTanggal(String mTanggal) {
        this.mTanggal = mTanggal;
    }

    public String getmRiwayat() {
        return mRiwayat;
    }

    public void setmRiwayat(String mRiwayat) {
        this.mRiwayat = mRiwayat;
    }

    public String getmTotal() {
        return mTotal;
    }

    public void setmTotal(String mTotal) {
        this.mTotal = mTotal;
    }

    public String getmBus() {
        return mBus;
    }

    public void setmBus(String mBus) {
        this.mBus = mBus;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public void setmImageResourceId(int mImageResourceId) {
        this.mImageResourceId = mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
