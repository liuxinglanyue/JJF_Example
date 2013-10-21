package com.cache.oscache;

public class CacheManager {

	private BaseCache newsCache;

	private static CacheManager instance;
	private static Object lock = new Object();

	public CacheManager() {
		// 这个根据配置文件来，初始BaseCache而已;
		newsCache = new BaseCache("news", 1800);
	}

	public static CacheManager getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new CacheManager();
				}
			}
		}
		return instance;
	}

	public void putNews(News news) {
		newsCache.put(news.getId(), news);
	}

	public void removeNews(String newsID) {
		newsCache.remove(newsID);
	}

	public News getNews(String newsID) {
		try {
			return (News) newsCache.get(newsID);
		} catch (Exception e) {
			System.out.println("getNews>>newsID[" + newsID + "]>>"
					+ e.getMessage());
			// News news = new News(newsID);

			News news = new News(newsID, "aaa", "bbb");
			this.putNews(news);
			return news;
		}
	}

	public void removeAllNews() {
		newsCache.removeAll();
	}

}