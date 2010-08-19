/**
 * @(#)AbstractFactory.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.factory;

import java.io.IOException;

import barrywei.igosyncdocs.bean.IGoPropertiesManager;
import barrywei.igosyncdocs.biz.IGoSyncDocsBiz;
import barrywei.igosyncdocs.dao.IGoSyncDocsDao;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class AbstractFactory {

	private static IGoSyncDocsBiz biz;
	private static IGoSyncDocsDao dao;

	public static IGoSyncDocsDao createSyncDocsDaoObject()
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException,IOException {
		if (dao == null) {
			String daoClassName = IGoPropertiesManager.getInstance().get("system.dao");
			dao = (IGoSyncDocsDao) Class.forName(daoClassName).newInstance();
		}
		return dao;
	}

	public static IGoSyncDocsBiz createSyncDocsBizObject()
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException,IOException {
		if(biz==null) {
			String bizClassName = IGoPropertiesManager.getInstance().get("system.biz");
			biz = (IGoSyncDocsBiz) Class.forName(bizClassName).newInstance();			
		}
		return biz;
	}
}
