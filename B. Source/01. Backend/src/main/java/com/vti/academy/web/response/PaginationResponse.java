package com.vti.academy.web.response;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.util.UriComponentsBuilder;

public class PaginationResponse<T> {

	private List<T> contents;
	private LinkResponse link;
	private MetadataResponse metadata;

	public PaginationResponse(Page<T> page, String baseUrl) {
		metadata = new MetadataResponse();
		metadata.setTotal(page.getTotalElements());
		metadata.setTotalPage(page.getTotalPages());
		metadata.setCurrentPage(page.getNumber());
		metadata.setSize(page.getSize());

		link = new LinkResponse();
		if ((page.getNumber() + 1) < page.getTotalPages()) {
			link.setNext(generateUri(baseUrl, page.getNumber() + 1, page.getSize()));
		}
		if ((page.getNumber()) > 0) {
			link.setPrev(generateUri(baseUrl, page.getNumber() - 1, page.getSize()));
		}
		int lastPage = 0;
		if (page.getTotalPages() > 0) {
			lastPage = page.getTotalPages() - 1;
		}
		link.setLast(generateUri(baseUrl, lastPage, page.getSize()));
		link.setFirst(generateUri(baseUrl, 0, page.getSize()));
		contents = page.getContent();
	}

	public List<T> getContents() {
		return contents;
	}

	public void setContents(List<T> contents) {
		this.contents = contents;
	}

	public LinkResponse getLink() {
		return link;
	}

	public void setLink(LinkResponse link) {
		this.link = link;
	}

	public MetadataResponse getMetadata() {
		return metadata;
	}

	public void setMetadata(MetadataResponse metadata) {
		this.metadata = metadata;
	}

	private String generateUri(String baseUrl, int page, int size) {
		return UriComponentsBuilder.fromUriString(baseUrl).queryParam("page", page).queryParam("size", size)
				.toUriString();
	}
}
