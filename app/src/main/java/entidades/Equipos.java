package entidades;

public class Equipos {
        private int id;
        private String nombreEq;
        private String paisEq;
        private String correoEq;
        private int campeonatos;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getnombreEq() {
            return nombreEq;
        }

        public void setnombreEq(String nombreEq) {
            this.nombreEq = nombreEq;
        }

        public String getpaisEq() {
            return paisEq;
        }

        public void setpaisEq(String paisEq) {
            this.paisEq = paisEq;
        }

        public String getcorreoEq() {
            return correoEq;
        }

        public void setcorreoEq(String correoEq) {
            this.correoEq = correoEq;
        }

        public int getCampeonatos() {
            return campeonatos;
        }

        public void setCampeonatos(int campeonatos) {
            this.campeonatos = campeonatos;
        }
}
