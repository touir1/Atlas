using Atlas_frontend.Utils.Validators;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class CompteModel
    {
        [JsonProperty("id")]
        public long? Id { get; set; }
        [JsonProperty("username")]
        [Required]
        [StringLength(25, MinimumLength =5)]
        public string Username { get; set; }
        [JsonProperty("password")]
        [Required]
        [PasswordPropertyText]
        [CustomPasswordFormatValidator(ErrorMessage = CustomPasswordFormatValidator.ValidationErrorMessage)]
        public string Password { get; set; }
        [JsonProperty("token")]
        public string Token { get; set; }
        [JsonProperty("roles")]
        public List<RoleModel> Roles { get; set; }
        [JsonProperty("user")]
        public UserModel User { get; set; }


    }
}
