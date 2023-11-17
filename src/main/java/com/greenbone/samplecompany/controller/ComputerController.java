package com.greenbone.samplecompany.controller;

import com.greenbone.samplecompany.dto.Computer;
import com.greenbone.samplecompany.dto.Computers;
import com.greenbone.samplecompany.dto.CrudComputerDto;
import com.greenbone.samplecompany.dto.UpdateEmployeeComputer;
import com.greenbone.samplecompany.exception.BadRequestException;
import com.greenbone.samplecompany.service.ComputerService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/computers")
@Validated
public class ComputerController {

    private final ComputerService computerService;

    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @PostMapping
    public Computer create(@Valid @RequestBody CrudComputerDto crudComputerDto) {

        if(crudComputerDto == null) {
            throw new BadRequestException("Empty Request Sent");
        }

        return computerService.createComputer(crudComputerDto);
    }

    @GetMapping
    public Computers getAll() {

        return computerService.getAllComputers();
    }

    @GetMapping("/{computerId}")
    public Computer get(@PathVariable Long computerId) {

        return computerService.getComputerDetails(computerId);
    }

    @PutMapping("/{computerId}")
    public Computer update(@PathVariable Long computerId, @Valid @RequestBody CrudComputerDto crudComputerDto) {

        if(crudComputerDto == null) {
            throw new BadRequestException("Empty Request Sent");
        }

        return computerService.updateComputer(computerId, crudComputerDto);
    }

    @DeleteMapping("/{computerId}")
    public void delete(@PathVariable Long computerId) {

        computerService.delete(computerId);
    }

    @PatchMapping("/{computerId}")
    public Computer updateEmployeeComputer(@PathVariable Long computerId,
                                           @Valid @RequestBody UpdateEmployeeComputer updateEmployeeComputer) {

        if(updateEmployeeComputer == null) {
            throw new BadRequestException("Empty Request Sent");
        }

        return computerService.updateEmployeeComputer(computerId, updateEmployeeComputer);
    }
}
