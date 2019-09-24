package com.finalproject.service;

import com.finalproject.dao.FinalProjectDAO;
import com.finalproject.dao.FinalProjectDAOInterface;
import com.finalproject.entity.FinalProjectEntity;

public class FinalProjectService implements FinalProjectServiceInterface {

	private FinalProjectService() {}
	public static FinalProjectServiceInterface createServiceObject(String string) {
		// TODO Auto-generated method stub
		return new FinalProjectService();
	}

	@Override
	public int createProfile(FinalProjectEntity fpe) {
		FinalProjectDAOInterface fdao = FinalProjectDAO.createObjectDAO("fdao");
		int i = fdao.createProfileDAO(fpe);
		return i;
	}
	@Override
	public int loginProfile(FinalProjectEntity fpe) {
		FinalProjectDAOInterface fdao = FinalProjectDAO.createObjectDAO("fdao");
		int i = fdao.createloginProfileDAO(fpe);
		return i;
	}

	
}
