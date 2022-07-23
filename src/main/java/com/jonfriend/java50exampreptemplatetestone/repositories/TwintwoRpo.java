package com.jonfriend.java50exampreptemplatetestone.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jonfriend.java50exampreptemplatetestone.models.TwintwoMdl;
import com.jonfriend.java50exampreptemplatetestone.models.TwinoneMdl;

@Repository
public interface TwintwoRpo extends CrudRepository<TwintwoMdl, Long> {
	
	List<TwintwoMdl> findAll();
	
	TwintwoMdl findByIdIs(Long id);
	
//	List<TwintwoMdl> findAllByTwinones(TwinoneMdl twinoneMdl);
	List<TwintwoMdl> findAllByTwinoneMdl(TwinoneMdl twinoneMdl);
	
//	List<TwintwoMdl> findByTwinonesNotContains(TwinoneMdl twinoneMdl);
	List<TwintwoMdl> findByTwinoneMdlNotContains(TwinoneMdl twinoneMdl);
}
