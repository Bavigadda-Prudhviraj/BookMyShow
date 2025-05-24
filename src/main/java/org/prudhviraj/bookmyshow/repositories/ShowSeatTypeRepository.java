package org.prudhviraj.bookmyshow.repositories;

import org.prudhviraj.bookmyshow.models.Show;
import org.prudhviraj.bookmyshow.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {


    public List<ShowSeatType> findAllByShow(Show show);
}
