"use client"

import React from "react"
import { unauthorized } from "next/navigation"

import { useAuthStore } from "@/stores/use-auth-store"

import { AppSidebar } from "./app-sidebar"
import { SiteHeader } from "./site-header"
import { LoadingSpinner } from "./ui/loading-spinner"
import { SidebarInset, SidebarProvider } from "./ui/sidebar"

type DashboardProps = {
  children?: React.ReactNode
}

const Dashboard: React.FC<DashboardProps> = ({ children }) => {
  const { auth, loading } = useAuthStore()

  if (loading) return <LoadingSpinner />

  if (!auth) unauthorized()

  return (
    <SidebarProvider
      style={
        {
          "--sidebar-width": "calc(var(--spacing) * 72)",
          "--header-height": "calc(var(--spacing) * 12)",
        } as React.CSSProperties
      }
    >
      <AppSidebar variant="inset" />
      <SidebarInset>
        <SiteHeader />
        <div className="flex flex-1 flex-col">
          <div className="@container/main flex flex-1 flex-col gap-2">
            <div className="flex flex-col gap-4 py-4 md:gap-6 md:py-6">
              {children}
            </div>
          </div>
        </div>
      </SidebarInset>
    </SidebarProvider>
  )
}
export default Dashboard
