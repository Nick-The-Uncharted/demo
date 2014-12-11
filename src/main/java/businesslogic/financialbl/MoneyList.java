package businesslogic.financialbl;

import java.io.Serializable;
import java.util.ArrayList;

import po.MoneyLineItemPO;

public class MoneyList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<MoneyLineItemPO> list;
	double total = 0;

	public MoneyList(){
		list = new ArrayList<MoneyLineItemPO>();
	}
	
	public ArrayList<MoneyLineItemPO> getList() {
		return list;
	}

	public void setList(ArrayList<MoneyLineItemPO> list) {
		this.list = list;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	public boolean addItem(MoneyLineItemPO item){
		total += item.getSum();
		return list.add(item);
	}

	public boolean delete(int row){
		return list.remove(row) != null;
	}
	
	public boolean update(Object value,int row,int col){
		MoneyLineItemPO item = list.get(row);
		switch (col) {
		case 0:
			item.setAccountName((String)value);
			break;
		case 1:
			item.setSum((Double)value);
			break;
		case 2:
			item.setComment((String)value);
			break;
		default:
			System.out.println("MoneyListUpdateError!");
		}
		return true;
	}
	
	public MoneyLineItemPO find(String accountId, double sum, String comment){
		for(MoneyLineItemPO item : list){
			if(item.match(accountId, sum, comment)){
				return item;
			}
		}
		return null;
	}
	
	public boolean containAccountName(String accountName){
		for(MoneyLineItemPO item : list){
			if(item.getAccountName().equals(accountName)){
				return true;
			}
		}
		return false;
	}
}