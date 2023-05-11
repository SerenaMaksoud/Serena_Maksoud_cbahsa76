package com.example.Anywr.api.controllers;

import com.example.Anywr.configuration.JWT.JwtTokenUtil;
import com.example.Anywr.datahandlers.models.AuthenticationRequest;
import com.example.Anywr.datahandlers.models.Responses.AuthenticationResponse;
import com.example.Anywr.datahandlers.models.Responses.StudentsResponse;
import com.example.Anywr.datahandlers.models.Students;
import com.example.Anywr.datahandlers.models.User;
import com.example.Anywr.datahandlers.services.Impl.UserServiceImpl;
import com.example.Anywr.datahandlers.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

@RestController("Controller")
@Component
@RequestMapping("StudentController")
public class Controller {
    @Resource
    private StudentService studentService;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JwtTokenUtil utils;
    @Autowired
    private UserServiceImpl userDetailsService;

    @Operation(summary = "Get the list of students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get the students"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "student not found",
                    content = @Content)})
    @RequestMapping(value = "/getStudents", method = RequestMethod.GET)
    public ResponseEntity<?> getStudents(
            @Parameter(name = "teachersFirstName", description = "The Teacher's first name", required = false, in = ParameterIn.QUERY)
            @RequestParam(value = "teachersFirstName", required = false) String teachersFirstName,
            @Parameter(name = "teacherLastName", description = "The Teacher's last name", required = false, in = ParameterIn.QUERY)
            @RequestParam(value = "teachersLastName", required = false) String teacherLastName,
            @Parameter(name = "className", description = "The Class's name", required = false, in = ParameterIn.QUERY)
            @RequestParam(value = "className", required = false) String className,
            @Parameter(name = "pageNumber", description = "For pagination to Specify which page to return starts with 0, default 0", in = ParameterIn.QUERY)
            @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
            @Parameter(name = "pageSize", description = "Specifies the number of students returned per page, default 10", in = ParameterIn.QUERY)
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestHeader(value = "token") String token,
            HttpServletRequest request) throws Exception {
        try {
            StudentsResponse response;
            // due to an error in authentication, authorization is disabled for testing purposes

//            String token = authorizationHeader.substring(7);
//            String username = jwtTokenUtil.getUsernameFromToken(token);
//            return ResponseEntity.ok(new JwtResponse(username));

            List<Students> students = studentService.getStudents(className, teachersFirstName, teacherLastName, pageNumber, pageSize);

            response = StudentsResponse.GetStudentsBuilder()
                    .status(0)
                    .students(students)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get the students"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "student not found",
                    content = @Content)})
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> auth(
            @RequestBody AuthenticationRequest authenticationRequest,
            HttpServletRequest request) throws Exception {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        User user = userDetailsService.loadByUsername(authenticationRequest.getUsername());

        UserDetails details = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };

        final String token = utils.generateToken(details);
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}
