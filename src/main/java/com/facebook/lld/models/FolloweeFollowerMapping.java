package com.facebook.lld.models;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FolloweeFollowerMapping {
	private Map<Long, Long> followeeFollowerIdMap;

}
