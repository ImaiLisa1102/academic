package cn.zsc.service;

import cn.zsc.entity.Term;
import cn.zsc.mapper.TermMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TermService {
    @Autowired
    private TermMapper termMapper;

    public Term findTermById(String termId) {
        return termMapper.findById(termId);
    }

    public List<Term> findAll(int offset, int size){
        return termMapper.findAll(offset, size);
    }

    public void deleteTerm(String termId) {
        termMapper.deleteTerm(termId);
    }

    public int count() {
        return termMapper.count();
    }

    public void insertTerm(Term term) {
        termMapper.insertTerm(term);
    }

    public List<String> getAllTermIds() {
        return termMapper.selectAllTermIds();
    }

    public boolean updateTermInfo(Term term) {
        return termMapper.updateTerm(term) > 0;
    }

    public void batchInsertTerms(List<Term> terms) {
        termMapper.batchInsert(terms);
    }
}
