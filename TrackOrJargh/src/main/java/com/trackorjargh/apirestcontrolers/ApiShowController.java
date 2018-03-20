package com.trackorjargh.apirestcontrolers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.annotation.JsonView;
import com.trackorjargh.commoncode.CommonCodeShow;
import com.trackorjargh.grafics.Grafics;
import com.trackorjargh.javaclass.CommentShow;
import com.trackorjargh.javaclass.DeleteElementsOfBBDD;
import com.trackorjargh.javaclass.PointShow;
import com.trackorjargh.javaclass.Shows;
import com.trackorjargh.javaclass.User;
import com.trackorjargh.javarepository.CommentShowRepository;
import com.trackorjargh.javarepository.PointShowRepository;
import com.trackorjargh.javarepository.ShowRepository;

public class ApiShowController {
	
	private final ShowRepository showRepository;
	private final PointShowRepository pointShowRepository;
	private final DeleteElementsOfBBDD deleteElementofBBDD;
	private final CommonCodeShow commonCode;
	private final CommentShowRepository commentShowRepository;
	
	@Autowired
	public ApiShowController (ShowRepository showRepository, PointShowRepository pointShowRepository, DeleteElementsOfBBDD deleteElementofBBDD, CommonCodeShow commonCode, CommentShowRepository commentShowRepository) {
		this.showRepository = showRepository;
		this.pointShowRepository = pointShowRepository;
		this.deleteElementofBBDD = deleteElementofBBDD;
		this.commonCode = commonCode;
		this.commentShowRepository = commentShowRepository;
	}
	
	@RequestMapping(value = "/api/series", method = RequestMethod.GET)
	@JsonView(Shows.BasicInformation.class)
	public Page<Shows> getSeries(Pageable page) {
		return showRepository.findAll(page);
	}
	
	@RequestMapping(value = "/api/series/graficomejorvaloradas", method = RequestMethod.GET)
	public List<Grafics> getBestPointShows() {
		List<Shows> shows = showRepository.findBestPointShow(new PageRequest(0, 10)).getContent();
		List<PointShow> listPoints;
		List<Grafics> graficShows = new ArrayList<>();
		double points;

		for (Shows show : shows) {
			points = 0;

			listPoints = pointShowRepository.findByShow(show);

			if (listPoints.size() > 0) {
				for (PointShow sf : listPoints)
					points += sf.getPoints();
				points /= listPoints.size();
			}

			graficShows.add(new Grafics(show.getName(), points));
		}

		return graficShows;
	}
	
	@RequestMapping(value = "/api/series/mejorvaloradas", method = RequestMethod.GET)
	@JsonView(Shows.BasicInformation.class)
	public Page<Shows> getBestPointSeries(Pageable page) {
		return showRepository.findBestPointShow(page);
	}
	
	@RequestMapping(value = "/api/busqueda/{optionSearch}/series/{name}/page", method = RequestMethod.GET)
	@JsonView(Shows.BasicInformation.class)
	public Page<Shows> getSearchSeries(Pageable page, @PathVariable String optionSearch, @PathVariable String name) {
		if (optionSearch.equalsIgnoreCase("titulo")) {
			return showRepository.findByNameContainingIgnoreCase(name, page);
		} else {
			return showRepository.findShowsByGender("%" + name + "%", page);
		}
	}
	
	@RequestMapping(value = "/api/serie/{name}")
	@JsonView(Shows.BasicInformation.class)
	public ResponseEntity<Shows> getShow(@PathVariable("name") String name) {
		Shows show = showRepository.findByNameIgnoreCase(name);
		if (show == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(show, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/api/agregarserie", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Shows> addShow(@RequestBody Shows show) {
		if (showRepository.findByNameIgnoreCase(show.getName()) == null) {
			showRepository.save(show);

			return new ResponseEntity<>(show, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.IM_USED);
		}

	}
	
	@RequestMapping(value = "/api/borrarserie/{name}", method = RequestMethod.DELETE)
	@JsonView(Shows.BasicInformation.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Shows> deleteShow(@PathVariable("name") String name) {
		Shows show = showRepository.findByNameIgnoreCase(name);
		if (show == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			deleteElementofBBDD.deleteShow(show);
			return new ResponseEntity<>(show, HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/api/editarserie/{name}", method = RequestMethod.PUT)
	@JsonView(Shows.BasicInformation.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Shows> editShow(@PathVariable String name, @RequestBody Shows show) {
		if (showRepository.findByNameIgnoreCase(name) == null) { // if the film does not exists, then i return
																	// a not found statement
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else { // if it exists, then i modify the item
			Shows editedShow = showRepository.findByNameIgnoreCase(name);

			return new ResponseEntity<>(
					commonCode.editShow(editedShow, show.getName(), show.getActors(), show.getDirectors(),
							show.getImage(), show.getGenders(), show.getSynopsis(), show.getTrailer(), show.getYear()),
					HttpStatus.OK);
		}
	}
	
	public interface basicInfoCommentShow extends CommentShow.BasicInformation, User.BasicInformation {
	}
	
	@RequestMapping(value = "/api/serie/comentarios/{name}", method = RequestMethod.GET)
	@JsonView(basicInfoCommentShow.class)
	public List<CommentShow> getCommentsShow(@PathVariable String name) {

		return showRepository.findByNameIgnoreCase(name).getCommentsShow();
	}
	
	@RequestMapping(value = "/api/serie/agregarcomentario/{name}", method = RequestMethod.POST)
	@JsonView(basicInfoCommentShow.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CommentShow> addComentShow(@PathVariable String name, @RequestBody CommentShow comment) {
		if (showRepository.findByNameIgnoreCase(name) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(
					commonCode.addCommentShow(showRepository.findByNameIgnoreCase(name), comment.getComment()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/api/serie/borrarcomentario/{id}", method = RequestMethod.DELETE)
	@JsonView(basicInfoCommentShow.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CommentShow> deleteShowComent(@PathVariable Long id) {
		if (commentShowRepository.findById(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(commonCode.deleteCommentShow(id), HttpStatus.OK);
		}
	}
	
	public interface joinedPointShowUserInfo extends PointShow.BasicInformation, Shows.NameShowInfo, User.NameUserInfo {
	}
	
	@RequestMapping(value = "/api/serie/agregarpuntos/{name}", method = RequestMethod.POST)
	@JsonView(joinedPointShowUserInfo.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PointShow> addShowPoint(@PathVariable String name, @RequestBody PointShow showPoint) {
		if (showRepository.findByNameIgnoreCase(name) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(
					commonCode.updatePointsShow(showRepository.findByNameIgnoreCase(name), showPoint.getPoints()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/api/serie/obtenerpuntos/{name}", method = RequestMethod.GET)
	@JsonView(joinedPointShowUserInfo.class)
	public List<PointShow> getShowPoint(@PathVariable String name) {

		return showRepository.findByNameIgnoreCase(name).getPointsShow();
	}

}
