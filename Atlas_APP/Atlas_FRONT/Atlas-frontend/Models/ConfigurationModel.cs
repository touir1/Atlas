using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class ConfigurationModel
    {
        [JsonProperty("id")]
        public long? Id { get; set; }
        [Required]
        [JsonProperty("key")]
        public string Key { get; set; }
        [Required]
        [JsonProperty("value")]
        public string Value { get; set; }
    }
}
