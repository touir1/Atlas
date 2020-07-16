using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class RoleModel
    {
        public long Id { get; set; }
        public String Libelle { get; set; }
        public List<PermissionModel> Permissions { get; set; }
        public List<CompteModel> Comptes { get; set; }



    }
}
