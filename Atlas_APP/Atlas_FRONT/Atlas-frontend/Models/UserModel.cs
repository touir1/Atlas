using Atlas_frontend.Utils;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class UserModel
    {
        [JsonProperty("id")]
        public long? Id { get; set; }
        [JsonProperty("nom")]
        public String Nom { get; set; }
        [JsonProperty("prenom")]
        public String Prenom { get; set; }
        [JsonProperty("email")]
        public String Email { get; set; }
        [JsonProperty("poste")]
        public String Poste { get; set; }
        [JsonProperty("image")]
        public String Image { get; set; }
        [JsonProperty("dateNaissance")]
        [JsonConverter(typeof(DateFormatConverter), "yyyy-MM-dd HH:mm:ss")]
        public DateTime? DateNaissance { get; set; }
        [JsonProperty("dateContrat")]
        [JsonConverter(typeof(DateFormatConverter), "yyyy-MM-dd HH:mm:ss")]
        public DateTime? DateContrat { get; set; }
        [JsonProperty("comptes")]
        public List<CompteModel> Comptes { get; set; }
        [JsonProperty("projetsCreated")]
        public List<ProjetModel> ProjetsCreated { get; set; }
        [JsonProperty("evaluationCreated")]
        public List<EvaluationModel> EvaluationCreated { get; set; }
        [JsonProperty("evaluations")]
        public List<EvaluationModel> Evaluations { get; set; }
        [JsonProperty("projets")]
        public List<ProjetModel> Projets { get; set; }
        [JsonProperty("frais")]
        public List<FraisModel> Frais { get; set; }
        [JsonProperty("absences")]
        public List<AbsenceModel> Absences { get; set; }
        [JsonProperty("reclamations")]
        public List<ReclamationModel> Reclamations { get; set; }
        [JsonProperty("userFormations")]
        public List<UserFormationModel> UserFormations { get; set; }
        [JsonProperty("rapports")]
        public List<RapportModel> Rapports { get; set; }



    }
}
