package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO create(@RequestBody ContactCreateDTO contactCreateDTO) {
        var contact = toEntity(contactCreateDTO);
        contactRepository.save(contact);
        return toContactDTO(contact);
    }

    private Contact toEntity(ContactCreateDTO contactCreateDTO) {
        var res = new Contact();
        res.setPhone(res.getPhone());
        res.setFirstName(res.getFirstName());
        res.setLastName(res.getLastName());
        return res;
    }

    private ContactDTO toContactDTO(Contact contact) {
        var res = new ContactDTO();
        res.setId(contact.getId());
        res.setFirstName(contact.getFirstName());
        res.setLastName(contact.getLastName());
        res.setPhone(contact.getPhone());
        res.setCreatedAt(contact.getCreatedAt());
        res.setUpdatedAt(contact.getUpdatedAt());
        return res;
    }
    // END
}
