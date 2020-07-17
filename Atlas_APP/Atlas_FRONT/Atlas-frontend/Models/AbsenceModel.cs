using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class AbsenceModel
    {
        [JsonProperty("id")]
        public long Id { get; set; }
        [JsonProperty("dateDebutConge")]
        public DateTime DateDebutConge { get; set; }
        [JsonProperty("dateFinConge")]
        public DateTime DateFinConge { get; set; }
        [JsonProperty("heures")]
        public float Heures { get; set; }
        [JsonProperty("status")]
        public String Status { get; set; }
        [JsonProperty("type")]
        public String Type { get; set; }
        [JsonProperty("user")]
        public UserModel User { get; set; }


    }
}
