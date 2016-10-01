using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using PerishableApp.Models;

namespace PerishableApp.Views
{
    public class tblPlacesController : Controller
    {
        private Perishableapp db = new Perishableapp();

        // GET: tblPlaces
        public ActionResult Index()
        {
            return View(db.tblPlaces.ToList());
        }

        // GET: tblPlaces/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            tblPlace tblPlace = db.tblPlaces.Find(id);
            if (tblPlace == null)
            {
                return HttpNotFound();
            }
            return View(tblPlace);
        }

        // GET: tblPlaces/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: tblPlaces/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Id,Name,Phone,Email,Address,City,Province,Country")] tblPlace tblPlace)
        {
            if (ModelState.IsValid)
            {
                db.tblPlaces.Add(tblPlace);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(tblPlace);
        }

        // GET: tblPlaces/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            tblPlace tblPlace = db.tblPlaces.Find(id);
            if (tblPlace == null)
            {
                return HttpNotFound();
            }
            return View(tblPlace);
        }

        // POST: tblPlaces/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Id,Name,Phone,Email,Address,City,Province,Country")] tblPlace tblPlace)
        {
            if (ModelState.IsValid)
            {
                db.Entry(tblPlace).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(tblPlace);
        }

        // GET: tblPlaces/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            tblPlace tblPlace = db.tblPlaces.Find(id);
            if (tblPlace == null)
            {
                return HttpNotFound();
            }
            return View(tblPlace);
        }

        // POST: tblPlaces/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            tblPlace tblPlace = db.tblPlaces.Find(id);
            db.tblPlaces.Remove(tblPlace);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
