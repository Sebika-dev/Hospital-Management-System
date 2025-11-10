package com.example.Hospital.Management.System.model;

public class Nurse extends MedicalStaff {
    private NurseQualificationLevel qualificationLevel;

    public Nurse() {
        super();
    }

    public Nurse(String id, String name, String departmentId, NurseQualificationLevel qualificationLevel) {
        super(id, name, departmentId);
        this.qualificationLevel = qualificationLevel;
    }

    public NurseQualificationLevel getQualificationLevel() {
        return qualificationLevel;
    }

    public void setQualificationLevel(NurseQualificationLevel qualificationLevel) {
        this.qualificationLevel = qualificationLevel;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", departmentId='" + getDepartmentId() + '\'' +
                ", qualificationLevel=" + qualificationLevel +
                '}';
    }
}
