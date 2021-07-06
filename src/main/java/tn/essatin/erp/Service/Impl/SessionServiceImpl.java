package tn.essatin.erp.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import tn.essatin.erp.Service.SessionService;
import tn.essatin.erp.dao.SessionDao;
import tn.essatin.erp.model.Session;

import java.util.List;

public class SessionServiceImpl implements SessionService {
    @Autowired
    SessionDao sessionDao;
    @Override
    public List<Session> getAll() {
        return sessionDao.findAll();
    }
}
