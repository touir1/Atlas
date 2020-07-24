using Atlas_frontend.Utils;
using Atlas_frontend.Utils.Extensions;
using Atlas_frontend.Utils.Validators;
using Microsoft.AspNetCore.Http;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class UserModel
    {
        [JsonProperty("id")]
        public long? Id { get; set; }
        [Required]
        [JsonProperty("nom")]
        public String Nom { get; set; }
        [Required]
        [JsonProperty("prenom")]
        [Display(Name = "Prénom")]
        public String Prenom { get; set; }
        [Required]
        [EmailAddress]
        [JsonProperty("email")]
        public String Email { get; set; }
        [Required]
        [JsonProperty("poste")]
        public String Poste { get; set; }
        [JsonProperty("image")]
        public String Image { get; set; }
        [DataType(DataType.Date)]
        [Display(Name = "Date de naissance")]
        [JsonProperty("dateNaissance")]
        [JsonConverter(typeof(DateFormatConverter), "yyyy-MM-dd HH:mm:ss")]
        public DateTime? DateNaissance { get; set; }
        [Required]
        [DataType(DataType.Date)]
        [Display(Name = "Date de contrat")]
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

        [JsonIgnore]
        [Display(Name = "Image de profil")]
        [CustomImageFileValidator(ErrorMessage ="Le format d'image est invalide")]
        public IFormFile ProfileImage { get; set; }
        [JsonIgnore]
        public Boolean Selected { get; set; }
        [JsonIgnore]
        public string FullName
        {
            get { return Nom.ToUpper() + " " + Prenom.Capitalize(); }
        }

        public override bool Equals(object obj)
        {
            return obj is UserModel model &&
                   Id == model.Id;
        }


    }
}
