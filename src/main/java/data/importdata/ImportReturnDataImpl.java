package data.importdata;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import po.ImportReturnPO;
import data.databaseutility.FileOperate;
import data.databaseutility.TimeNumber;
import dataservice.importdataservice.ImportReturnDataService;

public class ImportReturnDataImpl implements ImportReturnDataService{

	private static ImportReturnDataService importReturnDatabase;
	HashMap<String, ImportReturnPO> importReturnList;
	private int todayNumber;
	private ImportReturnDataImpl (){
		init();
	}
	
	public static ImportReturnDataService getInstance(){
		if (importReturnDatabase == null) {
			importReturnDatabase = new ImportReturnDataImpl();
		}
		return importReturnDatabase;
	}
	
	public boolean insert(ImportReturnPO po) {
		// TODO Auto-generated method stub
		if (importReturnList.containsKey(po.getReceiptsID())) {
			return false;
		}
		importReturnList.put(po.getReceiptsID(), po);
		todayNumber++;
		return true;
	}

	public void delete(String receiptsID) {
		// TODO Auto-generated method stub
		importReturnList.remove(receiptsID);
	}

	public void update(ImportReturnPO po) {
		// TODO Auto-generated method stub
		importReturnList.put(po.getReceiptsID(), po);
	}

	public ImportReturnPO find(String receiptsID) {
		// TODO Auto-generated method stub
		return importReturnList.get(receiptsID);
	}

	@SuppressWarnings("unchecked")
	public void init() {
		// TODO Auto-generated method stub
		TimeNumber timeNumber = (TimeNumber) new FileOperate("importReturnNumber.ser").read();
		if (timeNumber == null || timeNumber.isBeforeToday(Calendar.getInstance())) {
			todayNumber = 0;
		}else {
			todayNumber = timeNumber.getTodayNumber();
		}
		
		importReturnList = (HashMap<String, ImportReturnPO>)new FileOperate("src/importReturn.ser").read();
		if (importReturnList == null) {
			importReturnList = new HashMap<String, ImportReturnPO>();
		}
	}

	public void finish() {
		// TODO Auto-generated method stub
		TimeNumber timeNumber = new TimeNumber(todayNumber, Calendar.getInstance());
		new FileOperate("importReturnNumber.ser").write(timeNumber);
		new FileOperate("src/importReturn.ser").write(importReturnList);
	}

	public ArrayList<ImportReturnPO> getImportReturnList() {
		// TODO Auto-generated method stub
		ArrayList<ImportReturnPO> importReturnPOs = new ArrayList<ImportReturnPO>();
		importReturnPOs.addAll(importReturnList.values());
		return importReturnPOs;
	}

	public int getTodayNum() {
		// TODO Auto-generated method stub
		return todayNumber;
	}

}