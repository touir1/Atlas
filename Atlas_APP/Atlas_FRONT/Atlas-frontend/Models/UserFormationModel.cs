using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class UserFormationModel
    {
        [JsonProperty("status")]
        public String Status { get; set; }
        [JsonProperty("description")]
        public String Description { get; set; }
        [JsonProperty("dateDemande")]
        public DateTime? DateDemande { get; set; }
        [JsonProperty("formation")]
        public FormationModel Formation { get; set; }
        [JsonProperty("user")]
        public UserModel User { get; set; }
    }
}
