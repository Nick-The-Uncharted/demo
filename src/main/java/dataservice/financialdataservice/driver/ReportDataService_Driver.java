package dataservice.financialdataservice.driver;

import java.rmi.RemoteException;

import po.CaseReportInfoPO;
import po.CaseReportPO;
import po.ClientPO;
import po.GoodsPO;
import po.HistoryReportInfoPO;
import po.HistoryReportPO;
import po.HistoryReportType;
import po.SalesReportInfoPO;
import po.SalesReportPO;
import po.UserIdentity;
import po.UserPO;
import utility.TimePeriod;
import data.financialdata.stub.ReportDataServiceSerializableImplStub;
import dataservice.financialdataservice.ReportDataService;

public class ReportDataService_Driver {
	public void drive(ReportDataService reportDataService){
		TimePeriod period = null;
		try {
			period = new TimePeriod("2013-2-4","2014-5-2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GoodsPO good = null;
		try {
			good = new GoodsPO("X1","GN","X01",12,213,213,3432);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ClientPO client = new ClientPO();
		UserPO user = new UserPO("Nero","x902","sd2r2",UserIdentity.FinancialManager);
		CaseReportInfoPO caseReportInfo = new CaseReportInfoPO(period);
		CaseReportPO caseFetchResult = reportDataService.fetchCaseReport(caseReportInfo);
		if(caseFetchResult != null){
			System.out.println("fetch casereport pass");
		}else{
			System.out.println("fetch casereport failed");
		}
		
		HistoryReportInfoPO historyReportInfoPO = new HistoryReportInfoPO(period,HistoryReportType.FINANCIAL,client,user,"72");
		HistoryReportPO historyReportPO = reportDataService.fetchHistoryReport(historyReportInfoPO);
		if(historyReportPO != null){
			System.out.println("fetch historyreport pass");
		}else{
			System.out.println("fetch historyreport failed");
		}
		
		SalesReportInfoPO salesReportInfo = new SalesReportInfoPO(period, good, client, user, "62");
		SalesReportPO salesReportPO = reportDataService.fetchSalesReport(salesReportInfo);
		if(salesReportPO != null){
			System.out.println("fetch salesreport pass");
		}else{
			System.out.println("fetch salesreport failed");
		}
		
		reportDataService.init();
		reportDataService.finish();
	}
	
	public static void main(String[] args) {
		ReportDataService_Driver driver = new ReportDataService_Driver();
		ReportDataService reportDataService = new ReportDataServiceSerializableImplStub();
		driver.drive(reportDataService);
	}
}
