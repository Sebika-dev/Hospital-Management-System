package com.example.Hospital.Management.System.repository.infile;

import com.example.Hospital.Management.System.model.Room;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component("fileRoomRepository")
public class FileRoomRepository extends AbstractFileRepository<Room> {
    public FileRoomRepository() {
        super("data/rooms.json", Room.class);
    }
    @Override protected String getEntityId(Room entity) { return entity.getId(); }
    @Override protected void setEntityId(Room entity, String id) { entity.setId(id); }
    @Override protected String getIdPrefix() { return "ROOM"; }

    public List<Room> findByHospitalId(String hospitalId) {
        return storage.values().stream()
                .filter(room -> hospitalId.equals(room.getHospitalId()))
                .collect(Collectors.toList());
    }
}