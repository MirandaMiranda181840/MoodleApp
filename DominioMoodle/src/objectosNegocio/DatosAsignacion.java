/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectosNegocio;

/**
 *
 * @author Email
 */
public class DatosAsignacion {
    //DatosAsignacion asig = new DatosAsignacion(obj.getInt("id"), obj.getString("attemptnumber"), obj.getInt("timecreated"), obj.getInt("timemodified"), obj.getInt("grader"), obj.getInt("grade"));
    int id;
    int attemptnumber;
    int timecreated;
    int timemodified;
    int grader;
    float grade;
    int fechacalificada;

    public DatosAsignacion() {
    }

    public DatosAsignacion(int id, int attemptnumber, int timecreated, int timemodified, int grader, float grade, int fechacalificada) {
        this.id = id;
        this.attemptnumber = attemptnumber;
        this.timecreated = timecreated;
        this.timemodified = timemodified;
        this.grader = grader;
        this.grade = grade;
        this.fechacalificada = fechacalificada;
    }


    public int getFechacalificada() {
        return fechacalificada;
    }

    public void setFechacalificada(int fechacalificada) {
        this.fechacalificada = fechacalificada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAttemptnumber() {
        return attemptnumber;
    }

    public void setAttemptnumber(int attemptnumber) {
        this.attemptnumber = attemptnumber;
    }

    public int getTimecreated() {
        return timecreated;
    }

    public void setTimecreated(int timecreated) {
        this.timecreated = timecreated;
    }

    public int getTimemodified() {
        return timemodified;
    }

    public void setTimemodified(int timemodified) {
        this.timemodified = timemodified;
    }

    public int getGrader() {
        return grader;
    }

    public void setGrader(int grader) {
        this.grader = grader;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "DatosAsignacion{" + "id=" + id + ", attemptnumber=" + attemptnumber + ", timecreated=" + timecreated + ", timemodified=" + timemodified + ", grader=" + grader + ", grade=" + grade + ", fechacalificada=" + fechacalificada + '}';
    }

}
