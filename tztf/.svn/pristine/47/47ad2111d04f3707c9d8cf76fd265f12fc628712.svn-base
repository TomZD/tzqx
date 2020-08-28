package cn.movinginfo.tztf.common.utils;
/**
 * 判断点是否在某个区域里
 * @author wqb
 *
 */
public class PointInAreaUtil {
	
	/**
	 * 判断点是否在某个区域里
	 * @param LngCollotions 经度数组
	 * @param LatCollotions 纬度数组
	 * @param Lng 经度
	 * @param Lat 纬度
	 * @return
	 */
	public static boolean JudgeInOutFirst(double[] LngCollotions,double[] LatCollotions,double Lng,double Lat){
		//点在最值区域外
		if(Lng<GetMinLng(LngCollotions)||Lng>GetMaxLng(LngCollotions)||Lat<GetMinLat(LatCollotions)||Lat>GetMaxLat(LatCollotions)){
		return false;
		}else{
		int nvert =LngCollotions.length;//点数
		int i,j,c=0,k=0;
		//该点位与n个点所成n-1条线的判断
		for(i=0,j=nvert-1;i<nvert;j=i++){
		
		boolean LngTemp =(LngCollotions[i]==Lng);
		Boolean latTemp = (LatCollotions[i]==Lat);
		
		if(LngTemp&&latTemp){
         //点在点上
		return true;
		}else if(((LngCollotions[i]>=Lng) !=(LngCollotions[j]>=Lng))&&
		(Lat==(LatCollotions[j]-LatCollotions[i])*(Lng-LngCollotions[i])/(LngCollotions[j]-LngCollotions[i])+LatCollotions[i])) {
        //点在边界线上
		 return true;
		}else if(((LngCollotions[i]>=Lng) !=(LngCollotions[j]>=Lng))&&
		(Lat!=(LatCollotions[j]-LatCollotions[i])*(Lng-LngCollotions[i])/(LngCollotions[j]-LngCollotions[i])+LatCollotions[i])){
		//点不再边界线上
		double TempLat = (LatCollotions[j]-LatCollotions[i])*(Lng-LngCollotions[i])/(LngCollotions[j]-LngCollotions[i])+LatCollotions[i];//过点的横向线与边界线的交点
        //统计单一方向射线方向数
		 if(Lat<TempLat){
			 k++;
		 }
		}
	}
		//改点位与始点与终点所在线的判断
		if(((LngCollotions[0]>=Lng) !=(LngCollotions[LngCollotions.length-1]>=Lng))&&
		(Lat==(LatCollotions[LngCollotions.length-1]-LatCollotions[0])*(Lng-LngCollotions[i])/(LngCollotions[LngCollotions.length-1]-LngCollotions[0])+LatCollotions[0])){
		return true;
		}else if(((LngCollotions[0]>=Lng) !=(LngCollotions[LngCollotions.length-1]>=Lng))&&
		(Lat!=(LatCollotions[LngCollotions.length-1]-LatCollotions[0])*(Lng-LngCollotions[i])/(LngCollotions[LngCollotions.length-1]-LngCollotions[0])+LatCollotions[0])){
		//点不再边界线上
		double TempLat = (LatCollotions[LngCollotions.length-1]-LatCollotions[0])*(Lng-LngCollotions[0])/(LngCollotions[LngCollotions.length-1]-LngCollotions[0])+LatCollotions[0];//过点的横向线与边界线的交点
		//统计单一方向射线方向数
		if(Lat<TempLat){
		c++;
    }
		}
    System.out.println(c+"\t"+k);
		if((c+k)%2==0){
		return false;
		}else{
		return true;
		   }
		}
  }
	
	
	//工具相关

	public static double[] String2Double(String[] List){
	double[] GpsList=new double[List.length];
	for(int i=0;i<List.length;i++){
	GpsList[i]=Double.parseDouble(List[i]);
	}
	return GpsList;
	}
	
	public static double GetMaxLng(double[] LngCollotions){
	double MaxLng=LngCollotions[0];
	for(int i=0;i<LngCollotions.length;i++){
	if(MaxLng<LngCollotions[i]){
	MaxLng=LngCollotions[i];
	}
}
	return MaxLng;
	}
	
	public static double GetMinLng(double[] LngCollotions){
	double MinLng=LngCollotions[0];
	for(int i=0;i<LngCollotions.length;i++){
	if(MinLng>LngCollotions[i]){
	MinLng=LngCollotions[i];
	}
	}
	return MinLng;
	}
	
	public static double GetMaxLat(double[] LatCollotions){
	double MaxLat=LatCollotions[0];
	for(int i=0;i<LatCollotions.length;i++){
	if(MaxLat<LatCollotions[i]){
	MaxLat=LatCollotions[i];
	}
	}
	return MaxLat;
	}
	
	public static double GetMinLat(double[] LatCollotions){
	double MinLat=LatCollotions[0];
	for(int i=0;i<LatCollotions.length;i++){
	if(MinLat>LatCollotions[i]){
	MinLat=LatCollotions[i];
	   }
	}
	return MinLat;
	}
}
