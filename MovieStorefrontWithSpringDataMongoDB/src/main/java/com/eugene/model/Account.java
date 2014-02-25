package com.eugene.model;


//@Document
public class Account {

//	@Id
//	private String accountID;

	private Double total;

	public Account() {
	}

	public Account(Double total) {
		this.total = total;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

//	public String getAccountID() {
//		return accountID;
//	}
//
//	public void setAccountID(String accountID) {
//		this.accountID = accountID;
//	}
}
