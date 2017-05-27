package entities;


public class NhaCungCap {
   public String mMaNhaCungCap, mTenNhaCungCap, mNhomNhaCungCap, mMaCongNoNhaCungCap,
           mDiaChi, mEmail, mGhiChu;
   public int mTongMua;
   public NhaCungCap(){}
   public NhaCungCap(String maNhaCungCap, String tenNhaCungCap,
           String nhomNhaCungCap, String maCongNoNhaCungCap, String diaChi, String email,
           String ghiChu, int tongMua){
       this.mDiaChi = diaChi;
       this.mEmail = email;
       this.mGhiChu = ghiChu;
       this.mMaNhaCungCap = maNhaCungCap;
       this.mNhomNhaCungCap = nhomNhaCungCap;
       this.mTenNhaCungCap = tenNhaCungCap;
       this.mTongMua = tongMua;
       this.mMaCongNoNhaCungCap = maCongNoNhaCungCap;
       
   }
}
