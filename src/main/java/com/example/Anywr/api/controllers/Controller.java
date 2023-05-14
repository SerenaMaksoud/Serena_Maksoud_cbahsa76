package com.example.Anywr.api.controllers;

import com.example.Anywr.configuration.JWT.JwtTokenUtil;
import com.example.Anywr.datahandlers.models.AuthenticationRequest;
import com.example.Anywr.datahandlers.models.Responses.AuthenticationResponse;
import com.example.Anywr.datahandlers.models.Responses.BaseResponse;
import com.example.Anywr.datahandlers.models.Responses.StudentsResponse;
import com.example.Anywr.datahandlers.models.Student;
import com.example.Anywr.datahandlers.services.Impl.UserServiceImpl;
import com.example.Anywr.datahandlers.services.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController("Controller")
@Component
@RequestMapping("StudentController")
public class Controller {
    @Resource
    private StudentService studentService;
    @Autowired
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

            String username = utils.getUsernameFromToken(token);
            if (username == null)
                throw new Exception("Invalid Token");

            List<Student> students = studentService.getStudents(className, teachersFirstName, teacherLastName, pageNumber, pageSize);

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
            @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            //Get user (if exist) orm the database
            final UserDetails userDetails = userDetailsService.loadUser(authenticationRequest.getUsername(), authenticationRequest.getPassword());

            final String token = utils.generateToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponse(token));
        } catch (Exception e) {
            return getInternalServerErrorResponseAsJson(e.getMessage());
        }
    }

    public ResponseEntity<?> getInternalServerErrorResponseAsJson(String message) throws JsonProcessingException, UnsupportedEncodingException {
        BaseResponse resp;
        resp = BaseResponse.builder().
                status(-1)
                .message(message)
                .build();
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
