using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.IO;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Utils.Validators
{
    public class CustomImageFileValidator : ValidationAttribute
    {
        public override bool IsValid(object value)
        {
            if (value is IFormFile)
            {
                string[] permittedExtensions = { ".png", ".jpg", ".jpeg", ".gif", ".bmp" };
                IFormFile file = (IFormFile)value;

                var ext = Path.GetExtension(file.FileName).ToLowerInvariant();

                if (string.IsNullOrEmpty(ext) || !permittedExtensions.Contains(ext))
                {
                    // The extension is invalid ... discontinue processing the file
                    return false;
                }
                return true;
            }
            return true;
        }
    }
}
