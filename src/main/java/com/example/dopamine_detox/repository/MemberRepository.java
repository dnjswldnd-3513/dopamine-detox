package com.example.dopamine_detox.repository;

import com.example.dopamine_detox.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
// Entity 타입, pk 타입 이렇게 넣는것이다.
