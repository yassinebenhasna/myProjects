﻿//------------------------------------------------------------------------------
// <auto-generated>
//     Ce code a été généré à partir d'un modèle.
//
//     Des modifications manuelles apportées à ce fichier peuvent conduire à un comportement inattendu de votre application.
//     Les modifications manuelles apportées à ce fichier sont remplacées si le code est régénéré.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Sw2Rest
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Infrastructure;
    
    public partial class SWDBEntities2 : DbContext
    {
        public SWDBEntities2()
            : base("name=SWDBEntities2")
        {
        }
    
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            throw new UnintentionalCodeFirstException();
        }
    
        public virtual DbSet<Info> Infos { get; set; }
        public virtual DbSet<Master> Masters { get; set; }
        public virtual DbSet<Module> Modules { get; set; }
        public virtual DbSet<Personne> Personnes { get; set; }
    }
}