using Atlas_frontend.Utils;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class AbsenceModel
    {
        [JsonProperty("id")]
        public long? Id { get; set; }
        [Required]
        [DataType(DataType.Date)]
        [Display(Name = "Date début")]
        [JsonProperty("dateDebutConge")]
        [JsonConverter(typeof(DateFormatConverter), "yyyy-MM-dd HH:mm:ss")]
        public DateTime? DateDebutConge { get; set; }
        [DataType(DataType.Date)]
        [Display(Name = "Date fin")]
        [JsonProperty("dateFinConge")]
        [JsonConverter(typeof(DateFormatConverter), "yyyy-MM-dd HH:mm:ss")]
        public DateTime? DateFinConge { get; set; }
        [Required]
        [JsonProperty("heures")]
        public float? Heures { get; set; }
        [JsonProperty("status")]
        public String Status { get; set; }
        [Required]
        [JsonProperty("type")]
        public String Type { get; set; }
        [JsonProperty("user")]
        public UserModel User { get; set; }


    }
}
