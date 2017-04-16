package com.runningsnail.androiddemo.banner;

import java.util.ArrayList;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.runningsnail.androiddemo.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class BannerFragment extends Fragment{

	private View view;
	private OnIvClickListener listener;
	private ViewPager vp;
	private LinearLayout spotLayout;
	private ArrayList<BannerInfo>  infos= new ArrayList<>();
	private ArrayList<ImageView>  indicators= new ArrayList<>();
	private ImageView[]  points = new ImageView[5];
	private String[] imageUrls = {"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",  
            "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",  
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",  
            "http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",  
            "http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg"};
	private String[] contents = {"梦想在春天绽放","带梦起航","秋冬新款","汽车交易会","匠心独具"};
	private MyAdapter adapter;
	private int curpos = 0;
	private long releaseTime = 0; // 手指松开、页面不滚动时间，防止手机松开后短时间进行切换
	
	
	
	private Handler myHandler = new Handler(){
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				Log.e("tag", "--handleMessage----curpos="+curpos);
				 long now = System.currentTimeMillis();  
	                // 检测上一次滑动时间与本次之间是否有触击(手滑动)操作，有的话等待下次轮播  
	             if (now - releaseTime > 500) {  
	            	 vp.setCurrentItem(curpos+1, false);
	 				 myHandler.sendEmptyMessageDelayed(0, 1000); 
	             } else {
	            	 myHandler.removeMessages(0);
	            	 myHandler.sendEmptyMessageDelayed(0, 1000);   
	             }  
	             releaseTime = System.currentTimeMillis(); 
			}
		};
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_banner, null);
		 vp = (ViewPager) view.findViewById(R.id.banner_vp);
		 vp.setOnPageChangeListener(new MyPageChangeListener());
		 spotLayout = (LinearLayout) view.findViewById(R.id.banner_spot);
		 configImageLoader(); 
		 initView();
		 vp.setCurrentItem(1);
		 myHandler.sendEmptyMessageDelayed(0, 1000);
		 return view;
	}


	private void initView() {
		// TODO Auto-generated method stub
		infos.clear();
		indicators.clear();
		ImageView iv5 = new ImageView(getActivity());
		iv5.setScaleType(ImageView.ScaleType.FIT_XY);
		ImageLoader.getInstance().displayImage(imageUrls[4], iv5);
		indicators.add(iv5);
		for (int i = 0; i < imageUrls.length; i++) {
			
			BannerInfo info = new BannerInfo();
			info.setContent(contents[i]);
			info.setUrl(imageUrls[i]);
			infos.add(info);
			ImageView spot = new ImageView(getActivity());
			LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			lp.leftMargin = 20;
			spot.setLayoutParams(lp);
			points[i] = spot;
			spotLayout.addView(spot);
			ImageView iv = new ImageView(getActivity());
			iv.setScaleType(ImageView.ScaleType.FIT_XY);
			ImageLoader.getInstance().displayImage(imageUrls[i], iv);
			indicators.add(iv);
		}
		ImageView iv0 = new ImageView(getActivity());
		iv0.setScaleType(ImageView.ScaleType.FIT_XY);
		ImageLoader.getInstance().displayImage(imageUrls[0], iv0);
		indicators.add(iv0);
		for (int i = 0; i < points.length; i++) {
			if (i== 0) {
				points[i].setBackgroundResource(R.drawable.icon_point_pre);
			}else {
				points[i].setBackgroundResource(R.drawable.icon_point);
			}
		}
		adapter = new MyAdapter();
		vp.setAdapter(adapter);
	}


	
	
	public class MyAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return indicators.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			View view = indicators.get(position);
			container.removeView(view);
		}

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			// TODO Auto-generated method stub
			//Log.e("tag", "----instantiateItem----curpos="+curpos);
			View view = indicators.get(position);
			if (listener != null ) {
				view.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Log.e("tag", "----onClick----position="+position);
						//listener.onClick(position, infos.get(position).getContent());
					}
				});
				
				//listener.display(position-1, infos.get(curpos-1).getContent());
			}
			container.addView(view);
			return view;
		}
	}
	
	/** 
	 * 配置ImageLoder 
	 */  
	private void configImageLoader() {  
		// 初始化ImageLoader  
		DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.icon_stub) // 设置图片下载期间显示的图片  
				.showImageForEmptyUri(R.drawable.icon_empty) // 设置图片Uri为空或是错误的时候显示的图片  
				.showImageOnFail(R.drawable.icon_error) // 设置图片加载或解码过程中发生错误显示的图片  
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中  
				.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中  
				//.displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片  
				.build(); // 创建配置过得DisplayImageOption对象  
		
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity()).defaultDisplayImageOptions(options)  
				.threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()  
				.discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();  
		ImageLoader.getInstance().init(config);       
	}
	 interface OnIvClickListener{
		void onClick(int pos,String content);
		void display(int pos,String content);
	}
	 void setOnIvClickListener(OnIvClickListener listener){
		this.listener = listener;
	}
	 /** 
	     * ViewPager的监听器 
	     * 当ViewPager中页面的状态发生改变时调用 
	     *  
	     */  
	    private class MyPageChangeListener implements OnPageChangeListener{  
	        boolean isScrolled = false;  
	        @Override
	    	public void onPageScrollStateChanged(int arg0) {
	    		// TODO Auto-generated method stub
	    		switch (arg0) {  
	            case 1:// 手势滑动，空闲中  
	            	//Log.e("tag", "--------onPageScrollStateChanged----手势滑动，空闲中-------");
	            	isScrolled = false;
	                break;  
	            case 2:// 界面切换中  
	            	//Log.e("tag", "--------onPageScrollStateChanged----界面切换中-------");
	            	isScrolled = true;
	                break;  
	            case 0:// 滑动结束，即切换完毕或者加载完毕  
	            	vp.setCurrentItem(curpos,false);
	            	releaseTime = System.currentTimeMillis();
	            	if (listener !=null) {
	            		Log.e("tag", "--------onPageScrollStateChanged------curpos="+curpos);
	            		//listener.display(curpos, infos.get(curpos-1).getContent());
					}
	                break;  
	            }  
	    	}

	    	@Override
	    	public void onPageScrolled(int arg0, float arg1, int arg2) {
	    		// TODO Auto-generated method stub
	    		
	    	}

	    	@Override
	    	public void onPageSelected(int position) {
	    		// TODO Auto-generated method stub
	    		curpos = position;
	    		if (position == 0) {
	    			curpos = indicators.size() -2;
				}else if (position == indicators.size()-1) {
					curpos = 1;
				}
	    		Log.e("tag", "----onPageSelected----position="+position+"  size"+indicators.size());
	    		for (int i = 0; i < points.length; i++) {
	    			if (i== curpos -1) {
	    				points[i].setBackgroundResource(R.drawable.icon_point_pre);
	    			}else {
	    				points[i].setBackgroundResource(R.drawable.icon_point);
	    			}
	    		}
	    	}  
	          
	    } 
	    @Override
	    public void onDestroyView() {
	    	// TODO Auto-generated method stub
	    	super.onDestroyView();
	    	Log.e("tag", "----onDestroyView--------");
	    	myHandler.removeMessages(0);
	    }
}
