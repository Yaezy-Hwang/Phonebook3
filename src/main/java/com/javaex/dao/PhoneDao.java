package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {

	@Autowired
	private SqlSession sqlSession;

	// 사람 리스트(검색할때)
	public List<PersonVo> getPersonList() {
		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList");
		System.out.println(personList.toString());
		return personList;
	}

	 // 사람 추가
	public int personInsert(PersonVo personVo) {
		int count = sqlSession.insert("phonebook.insert", personVo);
		System.out.println(personVo.toString());
		
		return count;
	}
	
	public int personInsert2(String name, String hp, String company) {
		
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("name", name);
		pMap.put("hp", hp);
		pMap.put("company", company);
		
		int count = sqlSession.insert("phonebook.insert", pMap);
		System.out.println(pMap.toString());
		
		return count;
	}
	 
	// 사람정보
	public PersonVo getPerson(int personId) {
		PersonVo personVo = sqlSession.selectOne("phonebook.selectById", personId);
		System.out.println(personVo.toString());
		return personVo;
	}
	
	public Map<String, Object> getPerson2(int personId) {
		Map<String, Object> personMap = sqlSession.selectOne("phonebook.selectById2", personId);
		System.out.println(personMap.toString());
		return personMap;
	}

	/*
	 // 사람 리스트(검색안할때)
	 public List<PersonVo> getPersonList() {
	 	return getPersonList("");
	 }
	 */
	 
	 public int personUpdate(PersonVo personVo){
		 int count = sqlSession.update("phonebook.update", personVo);
		 return count;
		}
	 
	 // 사람 삭제
	 public int personDelete(int personId) {
		 int count = sqlSession.delete("phonebook.delete", personId);
		 return count;
	 }
	
	 

}
