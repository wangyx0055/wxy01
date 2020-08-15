package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.DeviceRecordDao;
import com.longersec.blj.domain.DeviceRecord;
import com.longersec.blj.domain.DTO.Deviceaccess;
import com.longersec.blj.service.DeviceRecordService;


@Transactional
@Service
public class DeviceRecordServiceImpl implements DeviceRecordService{

	@Autowired
	private DeviceRecordDao DeviceRecordDao;

	@Override
	public boolean editDeviceRecord(DeviceRecord deviceRecord) {
		// TODO Auto-generated method stub
		return this.DeviceRecordDao.editDeviceRecord(deviceRecord);
	}

	@Override
	public int addDeviceRecord(DeviceRecord deviceRecord) {
		// TODO Auto-generated method stub
		return this.DeviceRecordDao.addDeviceRecord(deviceRecord);
	}

	@Override
	public boolean delDeviceRecord(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.DeviceRecordDao.delDeviceRecord(ids);
	}

	@Override
	public List<Object> findAll(DeviceRecord deviceRecord, int page_start, int page_length) {
		return DeviceRecordDao.findAll(deviceRecord, page_start, page_length);
	}

	@Override
	public List<DeviceRecord> getEarlyRecord() {
		// TODO Auto-generated method stub
		return DeviceRecordDao.getEarlyRecord();
	}

	@Override
	public DeviceRecord getById(Integer id) {
		// TODO Auto-generated method stub
		return DeviceRecordDao.getById(id);
	}

	@Override
	public Integer getTextTotal() {
		// TODO Auto-generated method stub
		return DeviceRecordDao.getTextTotal();
	}

	@Override
	public Integer getGraphTotal() {
		// TODO Auto-generated method stub
		return DeviceRecordDao.getGraphTotal();
	}

	@Override
	public Integer get30DayTextIncrease() {
		// TODO Auto-generated method stub
		return DeviceRecordDao.get30DayTextIncrease();
	}

	@Override
	public Integer get30DayGraphIncrease() {
		// TODO Auto-generated method stub
		return DeviceRecordDao.get30DayGraphIncrease();
	}

	@Override
	public List<Object> selectTimeByInterval(String interval, String start_date, String end_date, int page_start,
			int page_length) {
		// TODO Auto-generated method stub
		if(interval.equals("hour")) {
			return DeviceRecordDao.selectTimeByHour(start_date, end_date, page_start, page_length);
		}else if(interval.equals("week")) {
			return DeviceRecordDao.selectTimeByWeek(start_date, end_date, page_start, page_length);
		}else if(interval.equals("month")) {
			return DeviceRecordDao.selectTimeByMonth(start_date, end_date, page_start, page_length);
		}else {
			return DeviceRecordDao.selectTimeByDay(start_date, end_date, page_start, page_length);
		}
	}

	@Override
	public List<Object> selectTimeByLong(String name, String start_date, String end_date, int page_start,
			int page_length) {
		// TODO Auto-generated method stub
		if(name.equals("device")) {
			return DeviceRecordDao.selectTimeLongByDevice(start_date, end_date, page_start, page_length);
		}else {
			return DeviceRecordDao.selectTimeLongByUser(start_date, end_date, page_start, page_length);
		}
	}

	@Override
	public List<Object> selectCommandReport( String start_date, String end_date, int page_start,
			int page_length) {
		// TODO Auto-generated method stub
		return DeviceRecordDao.selectCommandReport(start_date, end_date, page_start, page_length);
	}

	@Override
	public List<Object> selectAlertByInterval(String interval, String start_date, String end_date, int page_start,
			int page_length) {
		// TODO Auto-generated method stub

		if(interval.equals("hour")) {
			return DeviceRecordDao.selectAlertByHour(start_date, end_date, page_start, page_length);
		}else if(interval.equals("week")) {
			return DeviceRecordDao.selectAlertByWeek(start_date, end_date, page_start, page_length);
		}else if(interval.equals("month")) {
			return DeviceRecordDao.selectAlertByMonth(start_date, end_date, page_start, page_length);
		}else {
			return DeviceRecordDao.selectAlertByDay(start_date, end_date, page_start, page_length);
		}
	}


}
