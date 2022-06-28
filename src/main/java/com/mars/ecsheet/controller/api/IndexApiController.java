package com.mars.ecsheet.controller.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mars.ecsheet.entity.WorkBookEntity;
import com.mars.ecsheet.entity.WorkSheetEntity;
import com.mars.ecsheet.repository.WorkBookRepository;
import com.mars.ecsheet.repository.WorkSheetRepository;
import com.mars.ecsheet.utils.ApiResponseEnum;
import com.mars.ecsheet.utils.Result;
import com.mars.ecsheet.utils.SheetUtil;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

@RestController
@RequestMapping("/api")
public class IndexApiController {
	@Autowired
	private WorkBookRepository workBookRepository;

	@Autowired
	private WorkSheetRepository workSheetRepository;

	@GetMapping("index")
	public Result index() {
		List<WorkBookEntity> all = workBookRepository.findAll();

		return new Result(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(), all);
	}

	@GetMapping("index/create")
	public Result create() throws IOException {
		WorkBookEntity wb = new WorkBookEntity();
		wb.setName("default");
		wb.setOption(SheetUtil.getDefautOption());
		WorkBookEntity saveWb = workBookRepository.save(wb);
		// 生成sheet数据
		generateSheet(saveWb.getId());
		return new Result(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(), saveWb.getId());
	}

	@GetMapping("/index/{wbId}")
	public Result index(@PathVariable(value = "wbId") String wbId) {

		Optional<WorkBookEntity> Owb = workBookRepository.findById(wbId);
		WorkBookEntity wb = new WorkBookEntity();
		if (!Owb.isPresent()) {
			wb.setId(wbId);
			wb.setName("default");
			wb.setOption(SheetUtil.getDefautOption());
			WorkBookEntity result = workBookRepository.save(wb);
			generateSheet(wbId);
		} else {
			wb = Owb.get();
		}

		return new Result(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(), wb);
	}

	@PostMapping("/load/{wbId}")
	public String load(@PathVariable(value = "wbId") String wbId) {

		List<WorkSheetEntity> wsList = workSheetRepository.findAllBywbId(wbId);
		List<JSONObject> list = new ArrayList<>();
		wsList.forEach(ws -> {
			list.add(ws.getData());
		});
		System.out.println(JSONUtil.toJsonStr(list));
		return JSONUtil.toJsonStr(list);
	}

	@PostMapping("/loadSheet/{wbId}")
	public String loadSheet(@PathVariable(value = "wbId") String wbId) {
		List<WorkSheetEntity> wsList = workSheetRepository.findAllBywbId(wbId);
		List<JSONObject> list = new ArrayList<>();
		wsList.forEach(ws -> {
			list.add(ws.getData());
		});
		if (!list.isEmpty()) {
			return SheetUtil.buildSheetData(list).toString();
		}
		return SheetUtil.getDefaultAllSheetData().toString();
	}

	private void generateSheet(String wbId) {
		SheetUtil.getDefaultSheetData().forEach(jsonObject -> {
			WorkSheetEntity ws = new WorkSheetEntity();
			ws.setWbId(wbId);
			ws.setData(jsonObject);
			ws.setDeleteStatus(0);
			workSheetRepository.save(ws);
		});
	}
}
