package com.cit.eugene.web;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.eugene.model.Authorities;
import com.eugene.model.MovieReservation;
import com.eugene.model.User;
import com.eugene.model.VideoStoreMember;
import com.eugene.service.business.VideoStoreMemberService;
import com.eugene.web.VideoStoreMemberController;

public class VideoStoreMemberControllerTest {

	private VideoStoreMemberService videoStoreMemberManager = null;
	private VideoStoreMemberController videoStoreMemberController = null;
	private User u = null;

	@Before
	public void setUp() throws Exception {
		videoStoreMemberManager = createMock(VideoStoreMemberService.class);
		videoStoreMemberController = new VideoStoreMemberController(videoStoreMemberManager);
		u = new User();
		u.setEnabled(true);
		u.setUsername("bob");
		u.setPassword("password");
		Authorities a = new Authorities();
		a.setAuthority("ROLE_USER");
		a.setAuthoritiesId(1);
		Set<Authorities> l = new HashSet<Authorities>();
		l.add(a);
		u.setAuthoritieses(l);
	}

	@Test
	public void testGetAllVideoStoreMember() {
		expect(videoStoreMemberManager.getAllVideoStoreMember()).andReturn(new ArrayList<VideoStoreMember>());
		replay(videoStoreMemberManager);
		Iterable<VideoStoreMember> v = videoStoreMemberController.getAllVideoStoreMember();
		assertNotNull(v);
		verify(videoStoreMemberManager);
	}

	@Test
	public void testCreateVideoStoreMember() {
		VideoStoreMember vsm = new VideoStoreMember();
		vsm.setLocation("location");
		vsm.setName("bob");
		vsm.setVideoStoreMemberID("1111112");
		vsm.setUser(u);
		expect(videoStoreMemberManager.storeVideoStoreMember(vsm)).andReturn(vsm);
		replay(videoStoreMemberManager);
		videoStoreMemberController.createVideoStoreMember(vsm);
		verify(videoStoreMemberManager);
	}

	@Test
	public void testGetmovieReservationsForMember() {
		expect(videoStoreMemberManager.getVideoStoreMembersReservations(2l)).andReturn(new ArrayList<MovieReservation>());
		replay(videoStoreMemberManager);
		List<MovieReservation> l = videoStoreMemberController.getmovieReservationsForMember(2l);
		assertNotNull(l);
		verify(videoStoreMemberManager);
	}
}
