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
        public DateTime? DateNaissance { get; set; }
        [JsonProperty("dateContrat")]
        public DateTime? DateContrat { get; set; }
        [JsonProperty("compte")]
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
        [JsonProperty("reclamation")]
        public List<ReclamationModel> Reclamation { get; set; }
        [JsonProperty("userFormation")]
        public List<UserFormationModel> UserFormation { get; set; }
        [JsonProperty("rapport")]
        public List<RapportModel> Rapport { get; set; }



    }
}
