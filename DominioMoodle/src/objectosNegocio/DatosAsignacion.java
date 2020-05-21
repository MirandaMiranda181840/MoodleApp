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
    long timecreated;
    long timemodified;
    int grader;
    float grade;
    long fechacalificada;
    long fechaentrega;

    public DatosAsignacion() {
    }

    public DatosAsignacion(int id, int attemptnumber, long timecreated, long timemodified, int grader, float grade, long fechacalificada, long fechaentrega) {
        this.id = id;
        this.attemptnumber = attemptnumber;
        this.timecreated = timecreated;
        this.timemodified = timemodified;
        this.grader = grader;
        this.grade = grade;
        this.fechacalificada = fechacalificada;
        this.fechaentrega = fechaentrega;
    }

    public long getFechaentrega() {
        return fechaentrega;
    }

    public void setFechaentrega(long fechaentrega) {
        this.fechaentrega = fechaentrega;
    }

    public long getFechacalificada() {
        return fechacalificada;
    }

    public void setFechacalificada(long fechacalificada) {
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

    public long getTimecreated() {
        return timecreated;
    }

    public void setTimecreated(long timecreated) {
        this.timecreated = timecreated;
    }

    public long getTimemodified() {
        return timemodified;
    }

    public void setTimemodified(long timemodified) {
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
