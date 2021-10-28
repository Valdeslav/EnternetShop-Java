package controller;

import ioc.IocContainer;
public class ApplicationFinisher implements Runnable{
	private IocContainer container;
	
	public ApplicationFinisher(IocContainer container) {
		this.container=container;
	}
	public void run() {
		try {container.close();} catch(Exception e) {}
	}
}
